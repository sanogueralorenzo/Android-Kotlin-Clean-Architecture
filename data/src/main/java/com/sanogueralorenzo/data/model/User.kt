package com.sanogueralorenzo.data.model

import com.sanogueralorenzo.domain.model.Address
import com.sanogueralorenzo.domain.model.Company
import com.sanogueralorenzo.domain.model.Geo
import com.sanogueralorenzo.domain.model.User
import com.squareup.moshi.Json
import javax.inject.Inject

data class UserEntity(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "username") val username: String,
    @Json(name = "email") val email: String,
    @Json(name = "address") val addressEntity: AddressEntity,
    @Json(name = "phone") val phone: String,
    @Json(name = "website") val website: String,
    @Json(name = "company") val companyEntity: CompanyEntity
)

data class AddressEntity(
    @Json(name = "street") val street: String,
    @Json(name = "suite") val suite: String,
    @Json(name = "city") val city: String,
    @Json(name = "zipcode") val zipcode: String,
    @Json(name = "geo") val geoEntity: GeoEntity
)

data class GeoEntity(
    @Json(name = "lat") val lat: String,
    @Json(name = "lng") val lng: String
)

data class CompanyEntity(
    @Json(name = "name") val name: String,
    @Json(name = "catchPhrase") val catchPhrase: String,
    @Json(name = "bs") val bs: String
)

class UserMapper @Inject constructor(
    private val addressMapper: AddressMapper,
    private val companyMapper: CompanyMapper
) {

    fun mapToDomain(entity: UserEntity): User = User(
        id = entity.id,
        name = entity.name,
        username = entity.username,
        email = entity.email,
        address = addressMapper.mapToDomain(entity.addressEntity),
        phone = entity.phone,
        website = entity.website,
        company = companyMapper.mapToDomain(entity.companyEntity)
    )

    fun mapToDomain(list: List<UserEntity>): List<User> = list.map { mapToDomain(it) }

    fun mapToEntity(user: User): UserEntity = UserEntity(
        id = user.id,
        name = user.name,
        username = user.username,
        email = user.email,
        addressEntity = addressMapper.mapToEntity(user.address),
        phone = user.phone,
        website = user.website,
        companyEntity = companyMapper.mapToEntity(user.company)
    )

    fun mapToEntity(list: List<User>): List<UserEntity> = list.map { mapToEntity(it) }
}

class AddressMapper @Inject constructor(private val mapper: GeoMapper) {

    fun mapToDomain(entity: AddressEntity): Address = Address(
        street = entity.street,
        suite = entity.suite,
        city = entity.city,
        zipcode = entity.zipcode,
        geo = mapper.mapToDomain(entity.geoEntity)
    )

    fun mapToEntity(address: Address): AddressEntity = AddressEntity(
        street = address.street,
        suite = address.suite,
        city = address.city,
        zipcode = address.zipcode,
        geoEntity = mapper.mapToEntity(address.geo)
    )
}

class GeoMapper @Inject constructor() {

    fun mapToDomain(entity: GeoEntity): Geo = Geo(lat = entity.lat, lng = entity.lng)

    fun mapToEntity(geo: Geo): GeoEntity = GeoEntity(lat = geo.lat, lng = geo.lng)
}

class CompanyMapper @Inject constructor() {

    fun mapToDomain(entity: CompanyEntity): Company = Company(
        name = entity.name,
        catchPhrase = entity.catchPhrase,
        bs = entity.bs
    )

    fun mapToEntity(company: Company): CompanyEntity = CompanyEntity(
        name = company.name,
        catchPhrase = company.catchPhrase,
        bs = company.bs
    )
}
