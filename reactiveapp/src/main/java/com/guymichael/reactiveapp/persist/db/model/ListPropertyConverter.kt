package com.guymichael.reactiveapp.persist.db.model

import com.guymichael.reactiveapp.utils.JsonUtils
import io.objectbox.converter.PropertyConverter

open class ListPropertyConverter<T : Any>: PropertyConverter<List<T>, String> {
    final override fun convertToEntityProperty(databaseValue: String?): List<T>? {
        return if (databaseValue == null) {
            convertToEntityProperty_defaultIfDbValueNull()
        } else {
            JsonUtils.fromJson(databaseValue)
        }
    }

    final override fun convertToDatabaseValue(entityProperty: List<T>?): String? {
        return entityProperty?.let { JsonUtils.toJson(it) }
    }

    /** Default impl. returns null */
    open fun convertToEntityProperty_defaultIfDbValueNull(): List<T>? = null
}


//specific implementations for actual use (we can't use generics in the ObjectBox's @Convert annotation
class BooleanListPropertyConverter : ListPropertyConverter<Boolean>()
class IntListPropertyConverter : ListPropertyConverter<Int>()
class LongListPropertyConverter : ListPropertyConverter<Long>()
class DoubleListPropertyConverter : ListPropertyConverter<Double>()
class StringListPropertyConverter : ListPropertyConverter<String>()