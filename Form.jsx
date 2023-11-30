import { useState } from 'react';

export const Form = ({ selectedFlight, onPurchase }) => {
   const [formData, setFormData] = useState({
      firstName: '',
      lastName: '',
      email: '',
      phone: '',
   });

   const handleSubmit = (e) => {
      e.preventDefault();
      // Update the available seats and show success message
      onPurchase(formData);
   };

   return (
      <form onSubmit={handleSubmit} className="container_form">
         <label> First Name
            <input
               type="text"
               value={formData.firstName}
               onChange={(e) =>
                  setFormData({
                      ...formData, 
                      firstName: e.target.value 
                  })}
            />
         </label>

         <label> Last Name
            <input
               type="text"
               value={formData.lastName}
               onChange={(e) =>
                  setFormData({ 
                     ...formData, 
                     lastName: e.target.value 
                  })}
            />
         </label>

         <label> Email
            <input
               type="email"
               value={formData.email}
               onChange={(e) =>
                  setFormData({ 
                     ...formData, 
                     email: e.target.value 
                  })}
            />
         </label>
         <label> Phone
            <input
               type="tel"
               value={formData.phone}
               onChange={(e) =>
                  setFormData({ 
                     ...formData, 
                     phone: e.target.value 
                  })}
            />
         </label>

         <p>
            Ticket Information:
            <br />
            Flight Number: {selectedFlight.flightNumber}, 
            Price: ${selectedFlight.price}
         </p>
         <button type="submit">Submit</button>
      </form>
   );
};
