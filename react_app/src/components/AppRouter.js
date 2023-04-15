import React from 'react';
import {Routes, Route, Navigate} from 'react-router-dom';
import {OFFICER_PATH, PROFILE_PATH, USER_PATH, CRIME_PATH, CRIMINAL_PATH} from '../utils/paths';
import {Context} from '../index';
import Profile from '../pages/Profile';
import Auth from '../pages/Auth';
import Complaint from './Complaint';
import Officer from '../pages/Officer';
import {observer} from 'mobx-react-lite';
import Crime from '../pages/Crime';
import Criminal from '../pages/Criminal';

function AppRouter() {
    const {userInfo} = React.useContext(Context);
    const panel_path = userInfo.user?.role == null ? USER_PATH : OFFICER_PATH;
    return (
        <Routes>
            {
                userInfo.isAuthenticated && userInfo?.user?.role == null &&
                <>
                    <Route path={USER_PATH} element={<Complaint/>} exact/>
                </>
            }
            {
                userInfo?.user?.role != null &&
                <>
                    <Route path={OFFICER_PATH} element={<Officer/>} exact/>
                    <Route path={CRIME_PATH + ':id'} element={<Crime/>} exact/>
                    <Route path={CRIMINAL_PATH + ':id'} element={<Criminal/>} exact/>
                    <Route path={CRIMINAL_PATH} element={<Criminal/>} exact/>
                </>
            }
            {
                userInfo.isAuthenticated &&
                <>
                    <Route path={PROFILE_PATH} element={<Profile/>} exact/>
                    <Route path='/' element={<Navigate to={panel_path}/>}/>
                </>
            }
            {
                !userInfo.isAuthenticated && <Route path='/' element={<Auth/>}/>
            }
            <Route path='*' element={<p className='error-not-found'>Error 404. Wrong path</p>}/>
        </Routes>
    )
}

export default observer(AppRouter);