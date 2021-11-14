package main.kotlin.models

data class RoleResource(
    val role: Roles,
    val resource: String,
    val userId: Int = 0
) {
    companion object {
        fun isResource(resource: String, itemResource: String): Boolean {
            val resourceList = resource.split(".")
            val itemResourceList = itemResource.split(".")

            if (itemResourceList.count() > resourceList.count()) {
                return false
            }

            for (i in 0 until itemResourceList.count()) {
                if (itemResourceList[i] != resourceList[i]) {
                    return false
                }
            }
            return true
        }
    }
}