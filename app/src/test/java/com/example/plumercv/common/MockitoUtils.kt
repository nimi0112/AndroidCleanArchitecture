package com.example.plumercv.common

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

/**
 * Created by Nimish Nandwana on 24/08/2021.
 * Description -
 */

inline fun <reified T> mock() = Mockito.mock(T::class.java)
inline fun <T> whenever(methodCall: T) : OngoingStubbing<T> = Mockito.`when`(methodCall)