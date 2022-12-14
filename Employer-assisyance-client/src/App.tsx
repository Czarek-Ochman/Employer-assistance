import React from 'react';
import {BrowserRouter as Router, Routes, Route, BrowserRouter} from 'react-router-dom'
import './App.css';
import {Homepage} from "./Page/Home/Homepage";
import {Login} from "./Page/Login/Login";
import ResponsiveAppBar from "./Component/Shared/ResponsiveAppBar";
import {Signup} from "./Page/SignUp/Signup";


function App() {
  return (
      <div>
          <ResponsiveAppBar />
      <Routes>
        <Route path='*' element={<Homepage/>}/>
          <Route path='/login' element={<Login/>}/>
          <Route path='/rejestracja' element={<Signup/>}/>
      </Routes>
  </div>
  );
}

export default App;
