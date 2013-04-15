package pizzeria.swingui.threadsafety;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public aspect SwingThreadSafetyAspect {
	
	pointcut viewMethodCalls() : call(* javax..JComponent+.*(..));
	
	pointcut modelMethodCalls()
		: call(* javax..*Model+.*(..)) || call(* javax.swing.text.Document+.*(..));

	pointcut uiMethodCalls() : (viewMethodCalls() || modelMethodCalls());
	pointcut uiSyncMethodCalls() : call(* javax..JOptionPane+.*(..));
	
	pointcut threadSafeCalls()
		: call(void JComponent.revalidate())
	    || call(void JComponent.repaint(..))
		|| call(void add*Listener(EventListener))
		|| call(void remove*Listener(EventListener));
	
	pointcut excludedJoinpoints()
		: threadSafeCalls()
		|| within(SwingThreadSafetyAspect)
		|| if(EventQueue.isDispatchThread());
	
	pointcut routedMethods() : uiMethodCalls() && !excludedJoinpoints();
	
	pointcut voidReturnValueCalls() : call(void *.*(..));
	
	void around() 
		: routedMethods() && voidReturnValueCalls() && !uiSyncMethodCalls() 
	{
		Runnable worker = new Runnable() {
			public void run() {
				proceed();
			}};
			EventQueue.invokeLater(worker);
	}
	
	Object around() 
		: routedMethods() && (!voidReturnValueCalls() || uiSyncMethodCalls()) 
	{
		RunnableWithReturn worker = new RunnableWithReturn() {
			public void run() {
				_returnValue = proceed();
			}};
			
		try {
			EventQueue.invokeAndWait(worker);
		} catch (Exception ex) {
			// ... log exception
			return null;
		}
		return worker.getReturnValue();
	}
	
}