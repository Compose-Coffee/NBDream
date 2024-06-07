package kr.co.ui.icon

import androidx.compose.ui.graphics.vector.ImageVector
import kr.co.ui.icon.dreamicon.Add
import kr.co.ui.icon.dreamicon.Alarm
import kr.co.ui.icon.dreamicon.ArrowLeft
import kr.co.ui.icon.dreamicon.ArrowRight
import kr.co.ui.icon.dreamicon.Delete
import kr.co.ui.icon.dreamicon.Dropdown
import kr.co.ui.icon.dreamicon.Edit
import kr.co.ui.icon.dreamicon.Search
import kr.co.ui.icon.dreamicon.Spinner
import kr.co.ui.icon.dreamicon.Sprout
import kotlin.collections.List as ____KtList

public object DreamIcon

private var __AllIcons: ____KtList<ImageVector>? = null

public val DreamIcon.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons = listOf(Add, Alarm, ArrowLeft, ArrowRight, Delete, Dropdown, Edit, Search, Spinner,
        Sprout)
    return __AllIcons!!
  }
