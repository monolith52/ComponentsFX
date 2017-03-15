package searchtreeview

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableStringValue
import javafx.scene.control.TreeItem
import java.util.function.Predicate

/**
 * ツリー要素を検索条件によって追加削除するヘルパークラス
 *
 * 使用例:
 * <pre><code>
 * TreeItemFilter<String>(yourRootItem).bind( yourTextField.textProperty() )
 * </code></pre>
 */
class TreeItemFilter<T>(root: TreeItem<T>) {

    /**
     * ツリーアイテム間の関係を保持するリスト
     */
    private data class Relation<T>(val parent: TreeItem<T>, val items: List<TreeItem<T>>)
    private val relations: List<Relation<T>>
    /**
     * 検索条件となる文字列を持つプロパティ
     * bindメソッドによって設定してください
     */
    private var _searchProperty: ObservableStringValue? = null
    val searchProperty: ObservableStringValue?
    get() {return _searchProperty}
    /**
     * 各アイテムが表示される条件式
     */
    var predicate: Predicate<TreeItem<T>>
    val changeListener = ChangeListener<String> { observableValue, oldV, nweV -> updateRelations() }

    init {
        relations = buildRelations(root)
        predicate = Predicate { it.value.toString().toLowerCase().contains(searchProperty?.value?.toLowerCase() ?: "") }
    }

    /**
     * 監視対象のテキストプロパティを設定します。
     */
    fun bind(searchProperty: ObservableStringValue) {
        _searchProperty = searchProperty
        searchProperty.addListener( changeListener )
    }

    /**
     * テキストプロパティを監視対象から外します
     */
    fun unbind(searchProperty: ObservableStringValue) {
        searchProperty.removeListener( changeListener )
    }

    /**
     * ルートを渡すと再帰的に親子関係をリストにコピーして返します
     */
    private fun buildRelations(item: TreeItem<T>): List<Relation<T>> {
        val list = mutableListOf<Relation<T>>()
        item.children.forEach {list.addAll(buildRelations(it))}
        if (item.children.isNotEmpty()) list.add(Relation(item, item.children.toTypedArray().toList()))

        return list
    }

    /**
     * 検索条件に合った構造にツリーを変更します
     */
    private fun updateRelations() {
        relations.forEach { relation ->
            var index = 0
            relation.items.forEach { item ->
                val matched = predicate.test(item)
                val found = (relation.parent.children.find { it === item } != null)

                if (matched || item.children.isNotEmpty()) {
                    if (!found) relation.parent.children.add(index, item)
                    index++
                } else {
                    relation.parent.children.remove(item)
                }
            }
        }
    }
}