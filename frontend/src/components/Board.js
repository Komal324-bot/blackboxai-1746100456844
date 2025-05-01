import React from 'react';
import List from './List';

function Board({ board }) {
  return (
    <div className="bg-white rounded shadow p-4 w-80 flex flex-col">
      <h2 className="text-xl font-semibold mb-4">{board.title}</h2>
      <div className="flex flex-col gap-4">
        {board.lists && board.lists.map((list) => (
          <List key={list.id} list={list} />
        ))}
      </div>
    </div>
  );
}

export default Board;
