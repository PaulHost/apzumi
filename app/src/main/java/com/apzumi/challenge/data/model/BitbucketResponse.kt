package com.apzumi.challenge.data.model

import com.google.gson.annotations.SerializedName

data class BitbucketResponse(

	@field:SerializedName("values")
	val values: List<ValuesItem?>? = null
)

data class ValuesItem(

	@field:SerializedName("owner")
	val owner: BitbucketOwner? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null
)

data class Html(

	@field:SerializedName("href")
	val href: String? = null
)

data class Avatar(

	@field:SerializedName("href")
	val href: String? = null
)

data class Self(

	@field:SerializedName("href")
	val href: String? = null
)

data class BitbucketOwner(

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("display_name")
	val displayName: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("uuid")
	val uuid: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("account_id")
	val accountId: Any? = null,

	@field:SerializedName("nickname")
	val nickname: String? = null
)

data class Links(

	@field:SerializedName("self")
	val self: Self? = null,

	@field:SerializedName("html")
	val html: Html? = null,

	@field:SerializedName("avatar")
	val avatar: Avatar? = null
)
