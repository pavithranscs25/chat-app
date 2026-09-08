import React from "react";
import { Phone, Video, MoreVertical } from "lucide-react";
import Message from "./Message";
import MessageInput from "./MessageInput";

function ChatWindow({ chat, messages, onSend }) {

  return (
    <div className="flex-1 h-full bg-gray-50 flex flex-col">

      {/* Chat Header */}
      <div className="h-20 bg-white border-b border-gray-200
      flex items-center justify-between px-6">

        {/* User Details */}
        <div className="flex items-center gap-3">

          {/* Avatar */}
          <div className="relative">

            <div className="w-11 h-11 rounded-full bg-blue-600
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

          {/* Name + Status */}
          <div>

            <h2 className="font-semibold text-gray-900">
              {chat.name}
            </h2>

            <p className="text-xs text-green-500">
              {chat.online ? "Online" : "Offline"}
            </p>

          </div>

        </div>

        {/* Actions */}
        <div className="flex items-center gap-2">

          <button
            className="p-2.5 rounded-full
            hover:bg-gray-100 text-gray-600 transition"
          >
            <Phone size={19} />
          </button>

          <button
            className="p-2.5 rounded-full
            hover:bg-gray-100 text-gray-600 transition"
          >
            <Video size={20} />
          </button>

          <button
            className="p-2.5 rounded-full
            hover:bg-gray-100 text-gray-600 transition"
          >
            <MoreVertical size={20} />
          </button>

        </div>

      </div>


      {/* Messages Area */}
      <div className="flex-1 overflow-y-auto p-6">
 
       {messages.map((message) => (
            <Message
                key={message.id}
                message={message}
            />
  ))}

      </div>


      <div className="bg-white border-t border-gray-200 p-4">

        <MessageInput onSend={onSend} />

      </div>
    </div>
  );
}

export default ChatWindow;