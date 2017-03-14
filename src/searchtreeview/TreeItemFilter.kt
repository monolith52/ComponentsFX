package searchtreeview

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

    private data class Relation<T>(val item: TreeItem<T>, val parent: TreeItem<T>)
    private val relations: List<Relation<T>>
    private var _searchProperty: ObservableStringValue? = null
    val searchProperty: ObservableStringValue?
    get() {return _searchProperty}
    var predicate: Predicate<TreeItem<T>>

    init {
        relations = buildRelations(root)
        predicate = Predicate { it.value.toString().toLowerCase().contains(searchProperty?.value?.toLowerCase() ?: "") }
    }

    fun bind(searchProperty: ObservableStringValue) {
        _searchProperty = searchProperty
        searchProperty.addListener { observableValue, oldV, nweV -> updateRelations() }
    }

    private fun buildRelations(item: TreeItem<T>): List<Relation<T>> {
        val list = mutableListOf<Relation<T>>()
        item.children.forEach {list.addAll(buildRelations(it))}
        item.children.forEach {list.add(Relation<T>(it, item))}

        return list
    }

    private fun updateRelations() {
        val isWildcard = _searchProperty?.value?.trim()?.isEmpty() ?: true
        var index = 0
        var preParent: TreeItem<T>? = null

        relations.forEach { relation ->
            if (preParent != relation.parent) index = 0
            val matched = isWildcard || predicate.test(relation.item)
            val found = (relation.parent.children.find { it === relation.item } != null)

            // 検索文字にヒットしていれば表示
            // 表示中の子要素があれば無条件表示
            // relationsは深い順にソート済みなので子要素は全て判別済み
            if (matched || relation.item.children.size > 0) {
                if (!found) relation.parent.children.add(index, relation.item)
                index++
            } else {
                relation.parent.children.remove(relation.item)
            }
            preParent = relation.parent
        }
    }
}