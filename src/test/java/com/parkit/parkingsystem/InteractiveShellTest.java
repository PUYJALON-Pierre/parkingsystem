package com.parkit.parkingsystem;

import java.io.ByteArrayInputStream;

import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import com.parkit.parkingsystem.service.InteractiveShell;

import com.parkit.parkingsystem.util.InputReaderUtil;

@ExtendWith(MockitoExtension.class)
public class InteractiveShellTest {

  @Mock
  private static InputReaderUtil inputReaderUtil;

  boolean continueApp = true;

  @BeforeEach
  private void setUpForTest() {

    InputReaderUtil inputReaderUtil = new InputReaderUtil();

  }
@Disabled
  @Test
  public void loadInterfaceShutDownTest() {
    InteractiveShell.loadInterface();

    InputStream inputstream = new ByteArrayInputStream("3".getBytes());
    System.setIn(inputstream);

    // when(inputReaderUtil.readSelection()).thenReturn(3);

    AssertTrue(continueApp, false);
  }

  private void AssertTrue(boolean continueApp2, boolean b) {
    // TODO Auto-generated method stub

  }

}
