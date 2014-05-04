/** 
 *  Copyright (c) 2014  Ng Pan Wei
 *  
 *  Permission is hereby granted, free of charge, to any person 
 *  obtaining a copy of this software and associated documentation files 
 *  (the "Software"), to deal in the Software without restriction, 
 *  including without limitation the rights to use, copy, modify, merge, 
 *  publish, distribute, sublicense, and/or sell copies of the Software, 
 *  and to permit persons to whom the Software is furnished to do so, 
 *  subject to the following conditions: 
 *  
 *  The above copyright notice and this permission notice shall be 
 *  included in all copies or substantial portions of the Software. 
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS 
 *  BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN 
 *  ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 *  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
 *  SOFTWARE. 
 */
package ngpanwei.access.admin;

import ngpanwei.fw.AppException;
import ngpanwei.fw.HashMapUtil;
import ngpanwei.fw.ObjectFactory;

/**
 * User Administration.
 * @author ngpanwei
 */
public class UserAdminHandler implements IUserAdminHandler {
	@Override
	public UserAccount createUser(UserInfo userInfo) throws AppException {
		IUserDataAccess userDataAccess = getUserDataAccess();
		UserAccount account = userDataAccess.createUser(userInfo) ;
		return account;
	}
	@Override
	public UserAccount changePassword(UserInfo userInfo) throws AppException {
		IUserDataAccess userDataAccess = getUserDataAccess();
		UserAccount account = userDataAccess.readUser(userInfo) ;
		UserInfo newUserInfo = new UserInfo() ;
		HashMapUtil.copyKeyValues(account, newUserInfo);
		newUserInfo.put(UserInfo.PASSWORD, userInfo.get(UserInfo.PASSWORD)) ;
		account = userDataAccess.updateUser(newUserInfo) ;
		return account;
	}
	private IUserDataAccess getUserDataAccess() throws AppException {
		IUserDataAccess userDataAccess = (IUserDataAccess) ObjectFactory.getInstance(IUserDataAccess.class) ;
		if(userDataAccess==null) {
			throw new AppException(IUserDataAccess.class.getSimpleName() + " not initialized.") ;
		}
		return userDataAccess;
	}

}
