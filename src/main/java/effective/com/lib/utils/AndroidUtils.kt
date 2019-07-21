package effective.com.lib.utils

enum class AndroidKotlinMethod {
    OVERRIDE_ANDROID_LIFECYCLE,
    OVERRIDE_BASE_CLASS,
    OVERRIDE_INTERFACE,
    PUBLIC_ABSTRACT,
    PROTECTED_ABSTRACT,
    PUBLIC_OPEN,
    PROTECTED_OPEN,
    PUBLIC,
    PROTECTED,
    PRIVATE,
}

val ACTIVITY_METHODS = listOf(
    "onCreate",
    "onStart",
    "onRestoreInstanceState",
    "onRestart",
    "onResume",
    "onPause",
    "onSaveInstanceState",
    "onStop",
    "onDestroy"
)

val FRAGMENT_METHODS = listOf(
    "onAttach",
    "onCreate",
    "onCreateView",
    "onActivityCreated",
    "onStart",
    "onResume",
    "onPause",
    "onStop",
    "onDestroyView",
    "onDestroy"
)

val SERVICE_METHODS = listOf(
    "onCreate",
    "onStartCommand",
    "onBind",
    "onUnbind",
    "onRebind",
    "onDestroy"
)

val BROADCASTRECEIVER_METHODS = listOf(
    "onReceive"
)
