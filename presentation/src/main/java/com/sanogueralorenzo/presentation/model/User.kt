package com.sanogueralorenzo.presentation.model

import com.google.android.gms.maps.model.LatLng
import com.sanogueralorenzo.domain.model.Address
import com.sanogueralorenzo.domain.model.Company
import com.sanogueralorenzo.domain.model.Geo
import com.sanogueralorenzo.domain.model.User
import javax.inject.Inject

const val USER_ID_KEY = "USER_ID_KEY"

data class UserItem(val id: String,
                    val name: String,
                    val username: String,
                    val email: String,
                    val addressItem: AddressItem,
                    val phone: String,
                    val website: String,
                    val companyItem: CompanyItem)

data class AddressItem(val street: String,
                       val suite: String,
                       val city: String,
                       val zipcode: String,
                       val latLng: LatLng)

data class CompanyItem(val name: String,
                       val catchPhrase: String,
                       val bs: String)

class UserItemMapper @Inject constructor(private val addressItemMapper: AddressItemMapper,
                                         private val companyItemMapper: CompanyItemMapper) {

    fun mapToPresentation(user: User): UserItem = UserItem(id = user.id,
            name = user.name,
            username = user.username,
            email = user.email,
            addressItem = addressItemMapper.mapToPresentation(user.address),
            phone = user.phone,
            website = user.website,
            companyItem = companyItemMapper.mapToPresentation(user.company))

    fun mapToPresentation(list: List<User>): List<UserItem> = list.map { mapToPresentation(it) }

    fun mapToDomain(userItem: UserItem): User = User(id = userItem.id,
            name = userItem.name,
            username = userItem.username,
            email = userItem.email,
            address = addressItemMapper.mapToDomain(userItem.addressItem),
            phone = userItem.phone,
            website = userItem.website,
            company = companyItemMapper.mapToDomain(userItem.companyItem))

    fun mapToDomain(list: List<UserItem>): List<User> = list.map { mapToDomain(it) }
}


class AddressItemMapper @Inject constructor(private val mapper: LatLngMapper) {

    fun mapToPresentation(address: Address): AddressItem = AddressItem(street = address.street,
            suite = address.suite,
            city = address.city,
            zipcode = address.zipcode,
            latLng = mapper.mapToPresentation(address.geo))

    fun mapToDomain(addressItem: AddressItem): Address = Address(street = addressItem.street,
            suite = addressItem.suite,
            city = addressItem.city,
            zipcode = addressItem.zipcode,
            geo = mapper.mapToDomain(addressItem.latLng))
}

class LatLngMapper @Inject constructor() {

    fun mapToPresentation(geo: Geo): LatLng = LatLng(geo.lat.toDouble(), geo.lng.toDouble())

    fun mapToDomain(latLng: LatLng): Geo = Geo(lat = latLng.latitude.toString(), lng = latLng.longitude.toString())
}

class CompanyItemMapper @Inject constructor() {

    fun mapToPresentation(company: Company): CompanyItem = CompanyItem(name = company.name,
            catchPhrase = company.catchPhrase,
            bs = company.bs)

    fun mapToDomain(companyItem: CompanyItem): Company = Company(name = companyItem.name,
            catchPhrase = companyItem.catchPhrase,
            bs = companyItem.bs)
}