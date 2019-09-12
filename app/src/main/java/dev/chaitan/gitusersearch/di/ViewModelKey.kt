package dev.chaitan.gitusersearch.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)