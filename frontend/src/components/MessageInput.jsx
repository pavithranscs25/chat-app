import React, { useState } from "react";
import { Send, Smile } from "lucide-react";

function MessageInput({ onSend }) {

  const [text, setText] = useState("");

  const handleSend = () => {

    if (text.trim() === "") return;
        onSend(text);
        setText("");
    };

  const handleKeyDown = (e) => {

    if (e.key === "Enter") {
      handleSend();
    }

  };

  return (
    <div className="flex items-center gap-3">

      {/* Emoji Button */}
      <button
        className="p-2.5 rounded-full
        hover:bg-gray-100 text-gray-500 transition"
      >
        <Smile size={21} />
      </button>

      {/* Input */}
      <input
        type="text"
        placeholder="Type a message..."
        value={text}
        onChange={(e) => setText(e.target.value)}
        onKeyDown={handleKeyDown}
        className="flex-1 bg-gray-100 border border-gray-200
        rounded-xl px-4 py-3
        text-gray-900 placeholder-gray-400
        outline-none focus:border-blue-500
        focus:bg-white transition"
      />

      {/* Send Button */}
      <button
        onClick={handleSend}
        className="p-3 rounded-xl bg-blue-600
        text-white hover:bg-blue-700
        transition"
      >
        <Send size={19} />
      </button>

    </div>
  );
}

export default MessageInput;