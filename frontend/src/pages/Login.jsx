import React from "react";
import { FcGoogle } from "react-icons/fc";
import { useNavigate } from "react-router-dom";

function Login() {

  const navigate = useNavigate();

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center px-4">

      <div className="w-full max-w-md bg-indigo-950 border border-gray-800 rounded-2xl px-10 py-8 shadow-2xl">

        {/* Header */}
        <div className="text-center mb-8">

          <h1 className="text-3xl font-bold text-white">
            ✨ ChatApp
          </h1>

          <p className="text-gray-400 mt-2">
            Welcome back! Login with Google to continue chatting.
          </p>

        </div>

        {/* Google Login */}
        <button
          type="button"
          onClick={() => {
            window.location.href =
              "http://localhost:8080/oauth2/authorization/google";
          }}
          className="w-full py-3 bg-white hover:bg-gray-100
          text-gray-800 font-semibold rounded-lg transition
          flex items-center justify-center gap-3 cursor-pointer"
        >
          <FcGoogle size={22} />
          Continue with Google
        </button>

      </div>

    </div>
  );
}

export default Login;``