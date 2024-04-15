import React from 'react';
import Alert from '@mui/material/Alert';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';

import './updateError.scss'; // Import the SCSS file

const ProfileErrorComponent = () => {

  
  const gameEnvironment = () => {
    window.location.href = 'http://localhost:51915/';
  };
  

  return (
    <>
    <div>
    <Stack sx={{ width: '100%' }} spacing={20}>
        <Alert variant="filled" severity="warning" onClose={() => {}}>
          Please Update Your Profile First!!
        </Alert>
      </Stack>
    <center>
      <Button variant="contained" color="success" onClick={gameEnvironment} style={{ marginBottom: '50px' }}>
        Goto Game Environment
      </Button>
    </center>
    </div>
      
      
      
    </>

    

    
    
  );
}

export default ProfileErrorComponent;
