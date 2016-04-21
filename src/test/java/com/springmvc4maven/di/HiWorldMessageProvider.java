/**
 * 
 */
package com.springmvc4maven.di;

/**
 * @author TrueLiteTrace
 *
 */
public class HiWorldMessageProvider implements MessageProvider {

	/* (non-Javadoc)
	 * @see com.springmvc4maven.di.MessageProvider#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Hi World !!!";
	}

}
