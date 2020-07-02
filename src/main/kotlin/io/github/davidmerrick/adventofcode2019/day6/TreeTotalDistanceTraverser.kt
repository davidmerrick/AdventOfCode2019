package io.github.davidmerrick.adventofcode2019.day6

object TreeTotalDistanceTraverser {

    /**
     * Finds the total distance to all nodes
     */
    fun traverse(node: TreeNode, level: Int): Int {
        // Base case
        if (node.children.isEmpty()) {
            return level
        }

        var distance = level

        for (child in node.children) {
            distance += traverse(child, level + 1)
        }

        return distance
    }

    /**
     * Returns set of nodes between root and node specified by <value>
     */
    fun shortestPathTo(value: String, node: TreeNode, traversed: Set<TreeNode>): Set<TreeNode> {
        // Base case
        if (node.value == value) {
            return setOf(node)
        } else if (node.value != value && node.children.isEmpty()) {
            return setOf()
        }

        val _traversed = traversed.toMutableSet()

        for (child in node.children) {
            val pathSet = shortestPathTo(value, child, _traversed)
            if (pathSet.isNotEmpty()) {
                _traversed.add(node)
                _traversed.addAll(pathSet)
                break
            }
        }

        return _traversed
    }
}
