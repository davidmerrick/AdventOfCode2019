package io.github.davidmerrick.adventofcode2019.day6

import io.kotlintest.shouldBe
import org.testng.annotations.Test

/**
 * I implemented the orbit count as a tree.
 * The total number of orbits is the total distance
 * from the root to every node
 */
class TreeNodeTest {

    @Test
    fun `Create root and add child`() {
        val root = TreeNode("COM", null)
        root.addChild(TreeNode("A"))
        root.children.size shouldBe 1
    }

    @Test
    fun `Adding children should set parent`() {
        val root = TreeNode("COM", null)
        val a = TreeNode("A")
        val b = TreeNode("B")
        root.addChild(a)
        a.addChild(b)
        a.parent shouldBe root
        b.parent shouldBe a
    }

    @Test
    fun `Traverse total distance for single-level tree`() {
        val root = TreeNode("A", null)
        root.addChild(TreeNode("B", null))
        root.addChild(TreeNode("C", null))

        // Total count of orbits should be
        // b -> a
        // c -> a
        // so 2

        val count = TreeTotalDistanceTraverser.traverse(root, 0)
        count shouldBe 2
    }

    @Test
    fun `Traverse distance for two-level tree`() {
        val root = TreeNode("A", null)
        val b = TreeNode("B", null)
        root.addChild(b)
        b.addChild(TreeNode("C", null))

        // Total count of orbits should be
        // b -> a
        // c -> a
        // c -> b
        // so 3

        val count = TreeTotalDistanceTraverser.traverse(root, 0)
        count shouldBe 3
    }

    @Test
    fun `Traverse path to value`() {
        val root = TreeNode("A", null)
        val b = TreeNode("B", null)
        root.addChild(b)
        b.addChild(TreeNode("C", null))

        val nodeSet = TreeTotalDistanceTraverser.shortestPathTo("C", root, setOf())
        nodeSet.size shouldBe 3
    }
}
