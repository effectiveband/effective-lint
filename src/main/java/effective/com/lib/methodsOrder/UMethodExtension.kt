package effective.com.lib.methodsOrder

import com.android.tools.lint.client.api.JavaEvaluator
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiModifier
import com.intellij.psi.impl.compiled.ClsMethodImpl
import com.intellij.psi.impl.java.stubs.PsiClassStub
import org.jetbrains.uast.UMethod

fun UMethod.isAbstract() = hasModifierProperty(PsiModifier.ABSTRACT)

fun UMethod.isOpen() = hasModifierProperty(PsiModifier.OPEN)

fun UMethod.isPrivate() = hasModifierProperty(PsiModifier.PRIVATE)

fun UMethod.isProtected() = hasModifierProperty(PsiModifier.PROTECTED)

fun UMethod.isPublic() = hasModifierProperty(PsiModifier.PUBLIC)

fun UMethod.isOverrideInterface(): Boolean =
    findSuperMethods().find { it.containingClass?.isInterface == true }?.let { true } ?: false

fun UMethod.isOverrideBaseClass() =
    if (findSuperMethods().isEmpty()) false else !isOverrideInterface()

fun UMethod.getSuperClass() =
    ((getBaseSuperMethod(this) as? ClsMethodImpl)?.stub?.parentStub as? PsiClassStub)

fun getBaseSuperMethod(method: PsiMethod): PsiMethod {
    val superMethods = method.findSuperMethods()
    return if (superMethods.isNotEmpty()) getBaseSuperMethod(superMethods[0]) else method
}
