import React from "react";

function ChatList({ chats, selectedChat, setSelectedChat }) {
  return (
    <div className="flex-1 overflow-y-auto">

      {chats.length > 0 ? (

        chats.map((chat, index) => (

          <div
            key={chat.id}
            onClick={() => setSelectedChat(index)}
            className={`flex items-center gap-3 p-4 cursor-pointer
            border-b border-gray-100 transition
            ${
              selectedChat === index
                ? "bg-blue-50"
                : "hover:bg-gray-50"
            }`}
          >

            {/* Avatar */}
            <div className="relative">

              <div className="w-12 h-12 rounded-full bg-blue-600
              text-white flex items-center justify-center
              font-semibold">
                {chat.avatar}
              </div>

              {chat.online && (
                <span
                  className="absolute bottom-0 right-0
                  w-3.5 h-3.5 bg-green-500
                  border-2 border-white rounded-full"
                />
              )}

            </div>

            {/* Chat Information */}
            <div className="flex-1 min-w-0">

              <div className="flex justify-between">

                <h3 className="font-semibold text-gray-900 truncate">
                  {chat.name}
                </h3>

                <span className="text-xs text-gray-400">
                  {chat.time}
                </span>

              </div>

              <p className="text-sm text-gray-500 truncate mt-1">
                {chat.message}
              </p>

            </div>

          </div>

        ))

      ) : (

        <p className="text-center text-gray-400 mt-10">
          No chats found
        </p>

      )}

    </div>
  );
}

export default ChatList;