package effective.com.lib

object AndroidXmlUtils {

    val ALLOWED_PREFIXES = listOf(
        "activity_",
        "fragment_",
        "dialog_",
        "view_",
        "bottom_sheet_",
        "adapter_item_",
        "recycler_view_",
        "divider_",
        "space_",
        "popup_window_"
    )

    val IGNORE_PREFIXES = listOf(
        "activity",
        "fragment",
        "dialog",
        "view",
        "bottomSheet",
        "adapterItem",
        "recyclerView",
        "divider",
        "space",
        "popupWindow"
    )

    val ALL_ANDROID_WIDGETS = arrayOf(
        "AbsListView",
        "AbsSeekBar",
        "AbsSpinner",
        "AbsoluteLayout",
        "AccessibilityIterators",
        "ActivityChooserModel",
        "ActivityChooserView",
        "Adapter",
        "AdapterView",
        "AdapterViewAnimator",
        "AdapterViewFlipper",
        "Advanceable",
        "AlphabetIndexer",
        "AnalogClock",
        "AppSecurityPermissions",
        "ArrayAdapter",
        "AutoCompleteTextView",
        "BaseAdapter",
        "BaseExpandableListAdapter",
        "Button",
        "CalendarView",
        "CheckBox",
        "Checkable",
        "CheckedTextView",
        "Chronometer",
        "CompoundButton",
        "CursorAdapter",
        "CursorFilter",
        "CursorTreeAdapter",
        "DatePicker",
        "DateTimeView",
        "DialerFilter",
        "DigitalClock",
        "DoubleDigitManager",
        "EdgeEffect",
        "EditText",
        "Editor",
        "ExpandableListAdapter",
        "ExpandableListConnector",
        "ExpandableListPosition",
        "ExpandableListView",
        "FastScroller",
        "Filter",
        "FilterQueryProvider",
        "Filterable",
        "FrameLayout",
        "Gallery",
        "GridLayout",
        "GridView",
        "HeaderViewListAdapter",
        "HeterogeneousExpandableList",
        "HorizontalScrollView",
        "ImageButton",
        "ImageSwitcher",
        "ImageView",
        "LinearLayout",
        "ListAdapter",
        "ListPopupWindow",
        "ListView",
        "MediaController",
        "MultiAutoCompleteTextView",
        "NumberPicker",
        "OverScroller",
        "PopupMenu",
        "PopupWindow",
        "ProgressBar",
        "QuickContactBadge",
        "RadioButton",
        "RadioGroup",
        "RatingBar",
        "RelativeLayout",
        "RemoteViews",
        "RemoteViewsAdapter",
        "RemoteViewsListAdapter",
        "RemoteViewsService",
        "ResourceCursorAdapter",
        "ResourceCursorTreeAdapter",
        "ScrollBarDrawable",
        "ScrollView",
        "Scroller",
        "SearchView",
        "SectionIndexer",
        "SeekBar",
        "ShareActionProvider",
        "SimpleAdapter",
        "SimpleCursorAdapter",
        "SimpleCursorTreeAdapter",
        "SimpleExpandableListAdapter",
        "SlidingDrawer",
        "Space",
        "SpellChecker",
        "Spinner",
        "SpinnerAdapter",
        "StackView",
        "SuggestionsAdapter",
        "Switch",
        "TabHost",
        "TabWidget",
        "TableLayout",
        "TableRow",
        "TextClock",
        "TextSwitcher",
        "TextView",
        "TimePicker",
        "Toast",
        "ToggleButton",
        "TwoLineListItem",
        "VideoView",
        "ViewAnimator",
        "ViewFlipper",
        "ViewSwitcher",
        "WrapperListAdapter",
        "ZoomButton",
        "ZoomButtonsController",
        "ZoomControls"
    )
}
