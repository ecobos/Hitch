package org.lbc.hitch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lbc.hitch.domain.Trip;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * Created by Kelby on 4/7/2016.
 * Simple test showing how to use mocks
 * Right click on test in project folder and run with code coverage
 */
@RunWith(MockitoJUnitRunner.class)
public class TripTest {
    @Mock
    Trip mockTrip;

    // shared fields for tests
    String finalDest = "Long Beach";
    String departingDest = "Minneapolis";
    String userID = "123DGSA0";
    String time = "12:22";

    // stub
    Trip trip;


    @Before
    public void setup() {
        when(mockTrip.getEnd_point()).thenReturn(finalDest);
        when(mockTrip.getStart_point()).thenReturn(departingDest);
        when(mockTrip.getDriverId()).thenReturn(userID);
        when(mockTrip.getCancellationTime()).thenReturn(time);
        when(mockTrip.getDeparting_time()).thenReturn(time);
    }

    /**
     * Does not really need to be a mock since we want to test the object itself, but these mocks will
     * be really useful in the future when we don't want to create stubs of objects for testing purposes
     */
    @Test
    public void testMockedTrip() {
        assertEquals("Final destination does not match", finalDest, mockTrip.getEnd_point());
        assertEquals("Departing destination does not match", departingDest, mockTrip.getStart_point());
        assertEquals("Driver Id does not match", userID, mockTrip.getDriverId());
        assertEquals("Cancellation time is not a string", time, mockTrip.getCancellationTime());
        assertEquals("Departing time is not a string", time, mockTrip.getDeparting_time());
    }

    @Test
    public void testStub() {
        trip = new Trip(2,userID, 10, departingDest, finalDest, time, null, null);
        assertEquals("Driver Id does not match", userID, trip.getDriverId());
        assertEquals("Departing dest does not match", departingDest, trip.getStart_point());
        assertEquals("Final dest does not match", finalDest, trip.getEnd_point());
        assertEquals("Departing time does not match", time, trip.getDeparting_time());
        assertNull("Cancellation time should be null for new trip", trip.getCancellationTime());

    }
}
