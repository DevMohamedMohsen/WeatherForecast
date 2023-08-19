package com.weatherforecast.domain.feature.forecast.usecase

import com.weatherforecast.domain.feature.Resource
import com.weatherforecast.domain.feature.forecast.ForecastRepository
import com.weatherforecast.domain.feature.forecast.entity.Forecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.rmi.RemoteException

class GetForecastUseCaseTest {

    @Test
    fun `GetForecastUseCase() with city name when successful result Then return success status with list of forecast`() = runTest {
        // Arrange
        val repository = Mockito.mock(ForecastRepository::class.java)
        val city = Mockito.mock(String::class.java)
        val forecast = Mockito.mock(Forecast::class.java)
        Mockito.`when`(repository.getForecast(city)).thenReturn(Resource.Success(listOf(forecast)))

        // Act
        val result = GetForecastUseCase(Dispatchers.Main, repository)

        // Assert
        val expected = Resource.Success(listOf(forecast))
        assertEquals(expected, result)
    }

    @Test
    fun `GetForecastUseCase() with empty city name when failed result from the server Then return error status with exception`() = runTest {
        // Arrange
        val repository = Mockito.mock(ForecastRepository::class.java)
        val city = ""
        Mockito.`when`(repository.getForecast(city)).thenReturn(Resource.Error(RemoteException()))

        // Act
        val result = GetForecastUseCase(Dispatchers.Main, repository)

        // Assert
        val expected = Resource.Error(RemoteException())
        assertEquals(expected, result)
    }

    @Test
    fun `GetForecastUseCase() with city name when failed result from the server Then return error status with exception`() = runTest {
        // Arrange
        val repository = Mockito.mock(ForecastRepository::class.java)
        val city = Mockito.mock(String::class.java)
        Mockito.`when`(repository.getForecast(city)).thenReturn(Resource.Error(RemoteException()))

        // Act
        val result = GetForecastUseCase(Dispatchers.Main, repository)

        // Assert
        val expected = Resource.Error(RemoteException())
        assertEquals(expected, result)
    }
}