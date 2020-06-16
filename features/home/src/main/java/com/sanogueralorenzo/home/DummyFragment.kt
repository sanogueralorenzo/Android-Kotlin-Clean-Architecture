package com.sanogueralorenzo.home

import com.sanogueralorenzo.views.screen.ContainerFragment
import com.sanogueralorenzo.views.screen.simpleController

// TODO Dummy Fragment till Calendar is implemented
class DummyFragment : ContainerFragment() {
    override fun controller() = simpleController { }
    // display today tomorrow and following dates in carousel manner,
    // selecting fades previous selected text and selects new one
    // contain past events with a list of events in the past & the ones completed (with time)
    // contain future events with a list of the events for that day
}
