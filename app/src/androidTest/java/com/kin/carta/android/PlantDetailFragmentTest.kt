
package com.kin.carta.android

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlantDetailFragmentTest {

    /* @Rule
     @JvmField
     val activityTestRule = ActivityTestRule(GardenActivity::class.java)

     @Before
     fun jumpToPlantDetailFragment() {
         activityTestRule.activity.apply {
             runOnUiThread {
                 val bundle = Bundle().apply { putString("plantId", testPlant.plantId) }
                 findNavController(R.id.nav_host).navigate(R.id.plant_detail_fragment, bundle)
             }
         }
     }

     @Ignore("Share button redesign pending")
     @Test
     fun testShareTextIntent() {
         val shareText = activityTestRule.activity.getString(
             R.string.share_text_plant,
             testPlant.name
         )

         Intents.init()
         onView(withId(R.id.action_share)).perform(click())
         intended(
             chooser(
                 allOf(
                     hasAction(Intent.ACTION_SEND),
                     hasType("text/plain"),
                     hasExtra(Intent.EXTRA_TEXT, shareText)
                 )
             )
         )
         Intents.release()

         // dismiss the Share Dialog
         InstrumentationRegistry.getInstrumentation()
             .uiAutomation
             .performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
     }*/
}
