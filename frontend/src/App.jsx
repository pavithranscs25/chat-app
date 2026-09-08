import React from 'react'
import Login from "./pages/Login"
import Chat from './pages/Chat';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Requests from './pages/Request';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/chat" element={<Chat />} />
        <Route path="/requests" element={<Requests />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App