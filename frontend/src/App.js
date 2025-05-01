import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Board from './components/Board';

function App() {
  const [boards, setBoards] = useState([]);

  useEffect(() => {
    fetchBoards();
  }, []);

  const fetchBoards = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/boards');
      setBoards(response.data);
    } catch (error) {
      console.error('Error fetching boards:', error);
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <h1 className="text-3xl font-bold mb-6 text-center">Trello Clone</h1>
      <div className="flex flex-wrap gap-6 justify-center">
        {boards.map((board) => (
          <Board key={board.id} board={board} />
        ))}
      </div>
    </div>
  );
}

export default App;
