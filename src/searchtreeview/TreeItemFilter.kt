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

    private data class Relation<T>(val parent: TreeItem<T>, val items: List<TreeItem<T>>)
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
        if (item.children.isNotEmpty()) list.add(Relation(item, mutableListOf<TreeItem<T>>( *item.children.toTypedArray() )))

        return list
    }

    private fun updateRelations() {
        val isWildcard = _searchProperty?.value?.trim()?.isEmpty() ?: true

        relations.forEach { relation ->
            var index = 0
            relation.items.forEach { item ->
                val matched = isWildcard || predicate.test(item)
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