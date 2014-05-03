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

import java.util.HashMap;

import ngpanwei.fw.AppException;

/**
 * User Data Access class implemented with static List.
 * @author ngpanwei
 *
 */
public class UserDataAccess implements IUserDataAccess {
	protected HashMap<String,UserAccount> userAccounts ;
	public UserDataAccess() {
		reset() ;
	}
	public void reset() {
		userAccounts = new HashMap<String,UserAccount>() ;
	}

	@Override
	public UserAccount createUser(UserInfo userInfo) throws AppException {
		UserAccount account = userAccounts.get(userInfo.get(UserInfo.USERNAME)) ;
		if(account!=null) {
			throw new AppException(IUserDataAccess.UserExists) ;
		}
		account = new UserAccount() ;
		
		account.put(UserInfo.USERNAME, userInfo.get(UserInfo.USERNAME)) ;
		account.put(UserInfo.USEREMAIL, userInfo.get(UserInfo.USEREMAIL)) ;
		userAccounts.put(userInfo.get(UserInfo.USERNAME), account) ;
		return (UserAccount)account.clone();
	}

	@Override
	public UserAccount readUser(UserInfo userInfo) throws AppException {
		UserAccount account = userAccounts.get(userInfo.get(UserInfo.USERNAME)) ;
		if(account==null) {
			throw new AppException(IUserDataAccess.UserDoesNotExists) ;
		}
		return (UserAccount)account.clone();
	}

	@Override
	public UserAccount updateUser(UserInfo userInfo) throws AppException {
		UserAccount account = userAccounts.get(userInfo.get(UserInfo.USERNAME)) ;
		if(account==null) {
			throw new AppException(IUserDataAccess.UserDoesNotExists) ;
		}
		account.put(UserInfo.USERNAME, userInfo.get(UserInfo.USERNAME)) ;
		account.put(UserInfo.USEREMAIL, userInfo.get(UserInfo.USEREMAIL)) ;
		account.put(UserInfo.PASSWORD, userInfo.get(UserInfo.PASSWORD)) ;
		return (UserAccount)account.clone();
	}

	@Override
	public void deleteUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		
	}

}
