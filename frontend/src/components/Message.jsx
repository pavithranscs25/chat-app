import React from "react";

function Message({ message }) {

  const isMine = message.sender === "me";

  return (
    <div
      className={`flex mb-4 ${
        isMine ? "justify-end" : "justify-start"
      }`}
    >

      <div
        className={`max-w-[70%] px-4 py-3 rounded-2xl ${
          isMine
            ? "bg-blue-600 text-white rounded-br-md"
            : "bg-white text-gray-900 border border-gray-200 rounded-bl-md"
        }`}
      >

        {/* Message Text */}
        <p className="text-sm">
          {message.text}
        </p>

        {/* Time */}
        <p
          className={`text-[10px] mt-1 ${
            isMine
              ? "text-blue-100 text-right"
              : "text-gray-400"
          }`}
        >
          {message.time}
        </p>

      </div>

    </div>
  );
}

export default Message;