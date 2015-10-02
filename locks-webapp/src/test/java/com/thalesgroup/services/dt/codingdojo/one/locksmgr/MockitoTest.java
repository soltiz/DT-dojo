package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.Test;
public class MockitoTest {
	private class ClassToMock {
		public Long aFunction(int aParameter) {
			throw new UnsupportedOperationException();
		}
		
	}
	
	private void methodToTest(ClassToMock anInstance,int aParam) {
		anInstance.aFunction(aParam);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void NativeCallTest() {
		ClassToMock myTrueClassInstance=new ClassToMock();
		myTrueClassInstance.aFunction(4);
	}
	
	
	
	@Test
	public void CustomReturnsTest() {
		ClassToMock myMock=mock(ClassToMock.class);
		when(myMock.aFunction(4)).thenReturn(Long.valueOf(42),Long.valueOf(24));
		when(myMock.aFunction(18)).thenReturn(null);
		
		assertEquals(Long.valueOf(42),myMock.aFunction(4));
		assertEquals(null,myMock.aFunction(18));
		assertEquals(Long.valueOf(24),myMock.aFunction(4));

		
	}
	
	@Test
	public void explicitMockCallTest() {
		ClassToMock myMock=mock(ClassToMock.class);
		methodToTest(myMock,11);
		verify(myMock).aFunction(anyInt());
		methodToTest(myMock,17);
		verify(myMock).aFunction(17);

	}
	
}
