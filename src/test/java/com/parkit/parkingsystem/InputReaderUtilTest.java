package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.util.InputReaderUtil;



public class InputReaderUtilTest {

  InputReaderUtil inputReaderUtil;

  @BeforeEach
  private void setUpPerTest() {
    inputReaderUtil = new InputReaderUtil();
  }
@Disabled
  @Test
  public void readSelectionInputUser1() {
    // given creer input reader avec 1
    System.setIn( new ByteArrayInputStream("1".getBytes()));
    // when readselection
    int input = inputReaderUtil.readSelection();
    // then
    assertEquals(1, input);
  }

  
  @Disabled
  @Test
  public void readSelectionInputUser2() {
    // given creer input reader avec 1
    System.setIn(new ByteArrayInputStream("2".getBytes()));
    // when readselection
    int input = inputReaderUtil.readSelection();
    // then
    assertEquals(2, input);
  }
  @Disabled
  @Test
  public void readSelectionWrongInput() {
    // given creer input reader avec 1
    System.setIn(new ByteArrayInputStream("cdcx".getBytes()));
    // when readselection
    int input = inputReaderUtil.readSelection();
    // then
    assertEquals(-1, input);
  }
  @Disabled
  @Test
  public void readSelectionNoInput() {
    // given creer input reader avec 1
    System.setIn(new ByteArrayInputStream("".getBytes()));
    // when readselection
    int input = inputReaderUtil.readSelection();
    // then
    assertEquals(-1, input);
  }

}
