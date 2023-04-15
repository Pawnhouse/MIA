import './App.css';
import {useContext} from 'react';
import {Context} from '.';
import {BrowserRouter} from 'react-router-dom';
import AppRouter from './components/AppRouter';
import Header from './components/Header';
import 'bootstrap/dist/css/bootstrap.min.css';
import jwt_decode from "jwt-decode";
import {observer} from "mobx-react-lite";

function App() {
    const {userInfo} = useContext(Context);
    try {
        if (!userInfo.user) {
            userInfo.user = jwt_decode(localStorage.getItem('token'));
            userInfo.user.person.birthday = new Date(userInfo.user.person.birthday);
            userInfo.isAuthenticated = true;
        }
    } catch {
    }

    return (
        <BrowserRouter>
            <Header/>
            <AppRouter/>
        </BrowserRouter>
    );
}

export default observer(App);
