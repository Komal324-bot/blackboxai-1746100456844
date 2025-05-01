import React from 'react';
import Card from './Card';

function List({ list }) {
  return (
    <div className="bg-gray-100 rounded p-3">
      <h3 className="font-semibold mb-2">{list.title}</h3>
      <div className="flex flex-col gap-2">
        {list.cards && list.cards.map((card) => (
          <Card key={card.id} card={card} />
        ))}
      </div>
    </div>
  );
}

export default List;
