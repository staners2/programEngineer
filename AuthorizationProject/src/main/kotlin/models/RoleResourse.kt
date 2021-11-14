package main.kotlin.models

data class RoleResourse(
    val role: Roles,
    val resource: String,
    val userId: Int = 0,
) {
    companion object {
        fun isResourse(resource: String, itemResourse: String): Boolean {
            val resourceList = resource.split(".")
            val itemResourceList = itemResourse.split(".")

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
