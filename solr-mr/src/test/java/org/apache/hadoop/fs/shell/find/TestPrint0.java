/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.fs.shell.find;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.io.IOException;

import org.apache.hadoop.fs.shell.PathData;
import org.apache.hadoop.fs.shell.find.FindOptions;
import org.apache.hadoop.fs.shell.find.Print;
import org.apache.hadoop.fs.shell.find.Result;
import org.junit.Test;

import java.io.PrintStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.junit.Before;

public class TestPrint0 extends TestExpression {
  private static FileSystem fs;
  private static Configuration conf;

  @Before
  public void resetMock() throws IOException {
    MockFileSystem.reset();
    fs = new MockFileSystem();
    conf = fs.getConf();
  }
  
  @Test
  public void testPrint() throws IOException{
    Print.Print0 print = new Print.Print0();
    PrintStream out = mock(PrintStream.class);
    FindOptions options = new FindOptions();
    options.setOut(out);
    print.initialise(options);
    
    String filename = "/one/two/test";
    PathData item = new PathData(filename, conf);
    assertEquals(Result.PASS, print.apply(item));
    verify(out).println(filename + '\0');
    verifyNoMoreInteractions(out);
  }
}
