package com.sanogueralorenzo.views.composable

import com.airbnb.epoxy.EpoxyController
import com.sanogueralorenzo.views.ImageRowModelBuilder
import com.sanogueralorenzo.views.ImageRowModel_
import com.sanogueralorenzo.views.ImageTextRowModelBuilder
import com.sanogueralorenzo.views.ImageTextRowModel_
import com.sanogueralorenzo.views.SpaceRowModelBuilder
import com.sanogueralorenzo.views.SpaceRowModel_
import com.sanogueralorenzo.views.TextRowModelBuilder
import com.sanogueralorenzo.views.TextRowModel_
import com.sanogueralorenzo.views.textinput.TextInputLayoutRowModelBuilder
import com.sanogueralorenzo.views.textinput.TextInputLayoutRowModel_

/**
 * Model functions for EpoxyModelGroups to achieve similar DSL functionality on epoxy controllers
 */
inline fun EpoxyController.spaceRowModel(modelInitializer: SpaceRowModelBuilder.() -> Unit): SpaceRowModel_ =
    SpaceRowModel_().apply { modelInitializer() }

inline fun EpoxyController.textRowModel(modelInitializer: TextRowModelBuilder.() -> Unit): TextRowModel_ =
    TextRowModel_().apply { modelInitializer() }

inline fun EpoxyController.imageRowModel(modelInitializer: ImageRowModelBuilder.() -> Unit): ImageRowModel_ =
    ImageRowModel_().apply { modelInitializer() }

inline fun EpoxyController.imageTextRowModel(modelInitializer: ImageTextRowModelBuilder.() -> Unit):
    ImageTextRowModel_ = ImageTextRowModel_().apply { modelInitializer() }

inline fun EpoxyController.textInputLayoutRowModel(
    modelInitializer: TextInputLayoutRowModelBuilder.() -> Unit
): TextInputLayoutRowModel_ = TextInputLayoutRowModel_().apply { modelInitializer() }
