@file:Suppress("IllegalIdentifier")

package com.sanogueralorenzo.data.model

import com.sanogueralorenzo.data.createUser
import com.sanogueralorenzo.data.createUserEntity
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UserMapperTest {

    private lateinit var mapper: UserMapper

    @Before
    fun setUp() {
        mapper = UserMapper(addressMapper = AddressMapper(GeoMapper()), companyMapper = CompanyMapper())
    }

    @Test
    fun `map entity to domain`() {
        // given
        val entity = createUserEntity()

        // when
        val model = mapper.mapToDomain(entity)

        // then
        assertTrue(model.id == entity.id)
        assertTrue(model.name == entity.name)
        assertTrue(model.username == entity.username)
        assertTrue(model.email == entity.email)
        assertTrue(model.phone == entity.phone)
        assertTrue(model.website == entity.website)

        assertTrue(model.address.street == entity.addressEntity.street)
        assertTrue(model.address.suite == entity.addressEntity.suite)
        assertTrue(model.address.city == entity.addressEntity.city)
        assertTrue(model.address.zipcode == entity.addressEntity.zipcode)

        assertTrue(model.address.geo.lat == entity.addressEntity.geoEntity.lat)
        assertTrue(model.address.geo.lng == entity.addressEntity.geoEntity.lng)

        assertTrue(model.company.name == entity.name)
        assertTrue(model.company.catchPhrase == entity.companyEntity.catchPhrase)
        assertTrue(model.company.bs == entity.companyEntity.bs)
    }

    @Test
    fun `map domain to entity`() {
        // given
        val user = createUser()

        // when
        val entity = mapper.mapToEntity(user)

        // then
        assertTrue(entity.id == user.id)
        assertTrue(entity.name == user.name)
        assertTrue(entity.username == user.username)
        assertTrue(entity.email == user.email)
        assertTrue(entity.phone == user.phone)
        assertTrue(entity.website == user.website)

        assertTrue(entity.addressEntity.street == user.address.street)
        assertTrue(entity.addressEntity.suite == user.address.suite)
        assertTrue(entity.addressEntity.city == user.address.city)
        assertTrue(entity.addressEntity.zipcode == user.address.zipcode)

        assertTrue(entity.addressEntity.geoEntity.lat == user.address.geo.lat)
        assertTrue(entity.addressEntity.geoEntity.lng == user.address.geo.lng)

        assertTrue(entity.companyEntity.name == user.company.name)
        assertTrue(entity.companyEntity.catchPhrase == user.company.catchPhrase)
        assertTrue(entity.companyEntity.bs == user.company.bs)
    }
}
