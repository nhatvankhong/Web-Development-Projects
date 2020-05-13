import React from 'react';

import {BrowserRouter, Route} from "react-router-dom";

import Home from './components/Home';
import SignUp from './components/SignUp';
import Login from "./components/Login";
import SkillProfile from "./components/SkillProfile";
import JobList from "./components/JobList";

export default class App extends React.Component {
    render() {
        return (
            <BrowserRouter>
                <React.Fragment>
                    <Route path='/' exact component={Home}/>
                    <Route path='/login' exact component={Login}/>
                    <Route path='/sign-up' exact component={SignUp}/>
                    <Route path='/skill-profile' exact component={SkillProfile}/>
                    <Route path='/job-list' exact component={JobList}/>
                </React.Fragment>
            </BrowserRouter>
        )
    }
}
