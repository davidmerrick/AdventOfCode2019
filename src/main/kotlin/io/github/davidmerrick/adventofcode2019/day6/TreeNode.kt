package io.github.davidmerrick.adventofcode2019.day6

data class TreeNode(
        val value: String,
        var parent: TreeNode? = null
) {
    private val _children: MutableList<TreeNode> = mutableListOf()

    val children: List<TreeNode>
        get() = _children.toList()

    fun addChild(node: TreeNode) {
        node.parent = this
        _children.add(node)
    }
}
