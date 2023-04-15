import {useContext} from 'react';
import {NavLink} from 'react-router-dom';
import {Context} from '../index';
import {observer} from 'mobx-react-lite';
import {PROFILE_PATH} from '../utils/paths';


function Header() {
    const {userInfo} = useContext(Context);
    const user = userInfo.user;
    const name = user?.person?.name + ' ' + user?.person?.surname;
    const logOutAction = () => {
        userInfo.user = null;
        userInfo.isAuthenticated = false;
        localStorage.removeItem('token');
    }

    return (
        <header>
            <h2 style={{color: 'white', margin: '10px 20px'}}><NavLink className='simple-link hover-disable'
                                                                       to={'/'}>MIA</NavLink></h2>
            {
                userInfo.isAuthenticated &&
                <div className='profile'>
                    <NavLink className='simple-link mx-3' to={PROFILE_PATH}>{name}</NavLink>
                    <NavLink className='simple-link mx-3' to={'/'} onClick={logOutAction}>Log out</NavLink>
                </div>
            }

        </header>
    )
}

export default observer(Header);