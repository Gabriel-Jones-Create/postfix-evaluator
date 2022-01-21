package stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LinkedStackTest {

  private StackInterface<Integer> stack;
  
  private StackInterface<String> stringStack;

  @Before
  public void setup() {
    stack = new LinkedStack<Integer>();
    stringStack = new LinkedStack<String>();
  }

  @Test (timeout = 5000)
  public void testStack() {
    assertTrue("Stack should be empty after being constructed.", stack.isEmpty());
    assertEquals("Stack should contain zero elements after being constructed.", 0, stack.size());

    stack.push(5);
    assertFalse("Stack should not be empty.", stack.isEmpty());
    assertEquals("The top element should be 5", new Integer(5), stack.top());
    assertEquals("The stack should contain one element.", 1, stack.size());

    stack.push(4);
    assertEquals("The top element should be 4", new Integer(4), stack.top());
    assertEquals("The stack should contain two elements.", 2, stack.size());

    Integer t = stack.pop();
    assertEquals("The popped element should be 4", new Integer(4), t);
    assertEquals("The top element should be 5", new Integer(5), stack.top());
    assertEquals("The stack should contain one element.", 1, stack.size());
    assertFalse("The stack should not be empty.", stack.isEmpty());

    t = stack.pop();
    assertEquals("The popped element should be 5", new Integer(5), t);
    assertTrue("The stack should be empty.", stack.isEmpty());
    
  }
  @Test(timeout = 5000)
  public void testStringStack() {
	  assertTrue("Stack should be empty after being constructed.", stringStack.isEmpty());
	  assertEquals("Stack should contain zero elements after being constructed.", 0 , stringStack.size());
	  
	  stringStack.push("truck");
	  assertFalse("Stack should not be empty.", stringStack.isEmpty());
	  assertEquals("The top element should be truck", new String("truck"), stringStack.top());
	  assertEquals("The stack should contain one element.", 1, stringStack.size());
	  
	  stringStack.push("chicken");
	  assertEquals("The top element should be chicken", new String("chicken"), stringStack.top());
	  assertEquals("The stack should contain two elements.", 2, stringStack.size());
	  
	  String t = stringStack.pop();
	  assertEquals("The popped element should be chicken", new String("chicken"), t);
	  assertEquals("The top element should be truck", new String("truck"), stringStack.top());
	  assertEquals("The stack should contain one element.", 1, stringStack.size());
	  assertFalse("The stack should not be empty.", stringStack.isEmpty());
	  
	  t = stringStack.pop();
	  assertEquals("The popped element should be 5", new String("truck"), t);
	  assertTrue("The stack should be empty.", stack.isEmpty());
  }

  @Test (timeout = 5000, expected = StackUnderflowException.class)
  public void testStackUnderflowPop() {
    stack.pop();
  }

  @Test (timeout = 5000, expected = StackUnderflowException.class)
  public void testStackUnderflowTop() {
    stack.top();
  }
  @Test (timeout = 5000, expected = StackUnderflowException.class)
	  public void testStackUnderflowPush2AndPop3() {
		  stringStack.push("hi");
		  stringStack.push("there");
		  stringStack.pop();
		  stringStack.pop();
		  stringStack.pop();
	  }
  @Test (timeout = 5000, expected = StackUnderflowException.class)
  public void testStackUnderflowPush2AndPop2AndTop() {
	  stringStack.push("hi");
	  stringStack.push("there");
	  stringStack.pop();
	  stringStack.pop();
	  stringStack.top();
  }


}
