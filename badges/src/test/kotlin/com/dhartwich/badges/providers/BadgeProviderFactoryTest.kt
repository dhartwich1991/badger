package com.dhartwich.badges.providers

import android.content.Context
import android.content.pm.PackageManager
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class BadgeProviderFactoryTest {
    @Mock lateinit var context: Context
    @Mock lateinit var packageManager: PackageManager

    lateinit var badgeProviderFactory: BadgeProviderFactory

    @Before fun setUp() {
        MockitoAnnotations.initMocks(this)
        whenever(context.packageManager).thenReturn(packageManager)
    }

    @Test fun `on samsung launcher should create SamsungBadgeProvider`() {
        whenever(context.packageName).thenReturn(SamsungBadgeProvider.HOME_PACKAGE)
        badgeProviderFactory = BadgeProviderFactory(context)
        assertThat(badgeProviderFactory.provider is SamsungBadgeProvider).isTrue()
    }

    @Test fun `on HTC launcher should create HtcBadgeProvider`() {
        whenever(context.packageName).thenReturn(HtcBadgeProvider.HOME_PACKAGE)
        badgeProviderFactory = BadgeProviderFactory(context)
        assertThat(badgeProviderFactory.provider is HtcBadgeProvider).isTrue()
    }

    @Test fun `on Sony launcher should create SonyBadgeProvider`() {
        whenever(context.packageName).thenReturn(SonyBadgeProvider.HOME_PACKAGE)
        badgeProviderFactory = BadgeProviderFactory(context)
        assertThat(badgeProviderFactory.provider is SonyBadgeProvider).isTrue()
    }

    @Test fun `on LG launcher should create LgBadgeProvider`() {
        whenever(context.packageName).thenReturn(LgBadgeProvider.HOME_PACKAGE)
        badgeProviderFactory = BadgeProviderFactory(context)
        assertThat(badgeProviderFactory.provider is LgBadgeProvider).isTrue()
    }

    @Test fun `on unsupported launcher should create DefaultBadgeProvider`() {
        whenever(context.packageName).thenReturn("com.dhartwich.unsupportedLauncher")
        badgeProviderFactory = BadgeProviderFactory(context)
        assertThat(badgeProviderFactory.provider is DefaultBadgeProvider).isTrue()
    }
}