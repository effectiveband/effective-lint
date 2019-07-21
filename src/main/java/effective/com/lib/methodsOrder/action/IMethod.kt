package effective.com.lib.methodsOrder.action

interface IMethod {

    val STEP_WEIGHT: Int
	get() = 100

    fun calculateWeight(position: Int): Int
}
