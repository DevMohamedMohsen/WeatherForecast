package com.weatherforecast.domain.feature.country.usecase

import com.weatherforecast.domain.feature.Resource
import com.weatherforecast.domain.feature.country.CountryRepository
import com.weatherforecast.domain.feature.country.entity.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.rmi.RemoteException

class GetCountriesUseCaseTest {

    @Test
    fun `GetCountriesUseCase() when successful result Then return success status with list of countries`() = runTest {
        // Arrange
        val repository = Mockito.mock(CountryRepository::class.java)
        val county = Mockito.mock(Country::class.java)
        Mockito.`when`(repository.getCountries()).thenReturn(Resource.Success(listOf(county)))

        // Act
        val result = GetCountriesUseCase(Dispatchers.Main, repository)

        // Assert
        val expected = Resource.Success(listOf(county))
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `GetCountriesUseCase() when successful result Then return success status with empty list of countries`() = runTest {
        // Arrange
        val repository = Mockito.mock(CountryRepository::class.java)
        Mockito.`when`(repository.getCountries()).thenReturn(Resource.Success(emptyList()))

        // Act
        val result = GetCountriesUseCase(Dispatchers.Main, repository)

        // Assert
        val expected = Resource.Success(emptyList<Country>())
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `GetCountriesUseCase() when failed result from the server Then return error status with exception`() = runTest {
        // Arrange
        val repository = Mockito.mock(CountryRepository::class.java)
        val exception = Mockito.mock(RemoteException::class.java)
        Mockito.`when`(repository.getCountries()).thenReturn(Resource.Error(exception))

        // Act
        val result = GetCountriesUseCase(Dispatchers.Main, repository)

        // Assert
        val expected = Resource.Error(RemoteException())
        Assertions.assertEquals(expected, result)
    }
}