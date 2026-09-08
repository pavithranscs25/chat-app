import React, { useState } from "react";
import { Search, MoreVertical } from "lucide-react";
import ChatList from "./ChatList";
import { useNavigate } from "react-router-dom";

function Sidebar({
  chats,
  selectedChat,
  setSelectedChat,
  currentUser,
  handleLogout,
}) {
  const [search, setSearch] = useState("");
  const [showMenu, setShowMenu] = useState(false);
  const navigate = useNavigate();

  const filteredChats = chats.filter((chat) =>
    chat.name.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="w-full md:w-80 h-full bg-white border-r border-gray-200 flex flex-col">

      {/* Header */}
      <div className="p-5 border-b border-gray-200">

        <div className="flex items-center justify-between mb-5">

          <h1 className="text-2xl font-bold text-gray-900">
            Chat<span className="text-blue-600">App</span>
          </h1>

          {/* Three Dot Menu */}
          <div className="relative">

            <button
              onClick={() => setShowMenu(!showMenu)}
              className="p-2 rounded-full hover:bg-gray-100 text-gray-600"
            >
              <MoreVertical size={20} />
            </button>

            {showMenu && (
              <div className="absolute right-0 top-10 z-50 w-44 bg-white border border-gray-200 rounded-lg shadow-lg py-1">

                <button
                  onClick={() => {
                    console.log("Requests clicked");
                    setShowMenu(false);
                    navigate("/requests");
                  }}
                  className="w-full text-left px-4 py-2.5 text-sm text-gray-700 hover:bg-gray-100"
                >
                  Requests
                </button>

                <button
                  onClick={() => {
                    console.log("Settings clicked");
                    setShowMenu(false);
                  }}
                  className="w-full text-left px-4 py-2.5 text-sm text-gray-700 hover:bg-gray-100"
                >
                  Settings
                </button>

                <button
                  onClick={() => {
                    setShowMenu(false);
                    handleLogout();
                  }}
                  className="w-full text-left px-4 py-2.5 text-sm text-red-600 hover:bg-red-50"
                >
                  Logout
                </button>

              </div>
            )}

          </div>

        </div>

        {/* Current User */}
        {currentUser && (
          <div className="flex items-center gap-3 mb-4">
            <img
              src={currentUser.picture}
              alt="Profile"
              className="w-10 h-10 rounded-full"
            />

            <div className="min-w-0">
              <p className="font-semibold text-gray-900 truncate">
                {currentUser.name}
              </p>

            </div>
          </div>
        )}

        {/* Search */}
        <div className="relative">

          <Search
            size={18}
            className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400"
          />

          <input
            type="text"
            placeholder="Search chats..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className="w-full bg-gray-100 border border-gray-200
            text-gray-900 placeholder-gray-400
            rounded-xl py-2.5 pl-10 pr-4
            outline-none focus:border-blue-500
            focus:bg-white transition"
          />

        </div>

      </div>

      {/* Chat List */}
      <ChatList
        chats={filteredChats}
        selectedChat={selectedChat}
        setSelectedChat={setSelectedChat}
      />

    </div>
  );
}

export default Sidebar;