package br.com.logistic.operations.user_service.commons.enums

enum class UserCategoryEnum {
    NO_CATEGORY,
    DEFAULT_USER ,
    INTERNAL,
    MANAGER,
    DIRECTOR,
    REGIONAL_DIRECTOR,
    NATIONAL_DIRECTOR,
    PRESIDENT;

    companion object {
        fun getCategory(categoryName : String) : UserCategoryEnum? {
            for (entry in entries) {
                if(entry.name.equals(categoryName, ignoreCase = true)){
                    return entry;
                }
            }
            return null;
        }
    }

}