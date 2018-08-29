@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.presentation.model

import com.sanogueralorenzo.presentation.createUser
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UserItemMapperTest {

    private lateinit var mapper: UserItemMapper

    @Before
    fun setUp() {
        mapper = UserItemMapper(
            addressItemMapper = AddressItemMapper(LatLngMapper()),
            companyItemMapper = CompanyItemMapper()
        )
    }

    @Test
    fun `map domain to presentation`() {
        // given
        val user = createUser()

        // when
        val userItem = mapper.mapToPresentation(user)

        // then
        assertTrue(userItem.id == user.id)
        assertTrue(userItem.name == user.name)
        assertTrue(userItem.username == user.username)
        assertTrue(userItem.email == user.email)
        assertTrue(userItem.phone == user.phone)
        assertTrue(userItem.website == user.website)

        assertTrue(userItem.addressItem.street == user.address.street)
        assertTrue(userItem.addressItem.suite == user.address.suite)
        assertTrue(userItem.addressItem.city == user.address.city)
        assertTrue(userItem.addressItem.zipcode == user.address.zipcode)

        assertTrue(userItem.addressItem.latLng.latitude.toString() == user.address.geo.lat)
        assertTrue(userItem.addressItem.latLng.longitude.toString() == user.address.geo.lng)

        assertTrue(userItem.companyItem.name == user.name)
        assertTrue(userItem.companyItem.catchPhrase == user.company.catchPhrase)
        assertTrue(userItem.companyItem.bs == user.company.bs)
    }
}
