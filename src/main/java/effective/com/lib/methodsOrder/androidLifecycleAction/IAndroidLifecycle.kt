package effective.com.lib.methodsOrder.androidLifecycleAction

import org.jetbrains.uast.UMethod

interface IAndroidLifecycle {

    fun getAllMethods(): List<String>

    fun getMethodWeight(method: UMethod): Int {
        val allMethods = getAllMethods()
        val position = allMethods.indexOf(method.name)
        return if (position == -1) -1 else allMethods.size - position
    }
}