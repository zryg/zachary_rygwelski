/*
 * Copyright 2016 Benedikt Ritter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.britter.springbootherokudemo;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.github.britter.springbootherokudemo.controllers.HomeController;
import com.github.britter.springbootherokudemo.model.Account;
import com.github.britter.springbootherokudemo.repository.AccountRepository;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.ModelMap;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;

@RunWith(HierarchicalContextRunner.class)
public class HomeControllerTest {

    private ModelMap map;
    private HomeController ctrl;
    private AccountRepository repository;

    @Before
    public void setUp() throws Exception {
        map = new ModelMap();
        repository = mock(AccountRepository.class);
        ctrl = new HomeController(repository);
    }

    public class Home {

        @Test
        public void shouldAddInsertRecordToModelMap() throws Exception {
            ctrl.home(map);

            assertThat(map, hasKey("insertAccount"));
            assertTrue(map.get("insertAccount") instanceof Account);

            Account insertAccount = (Account) map.get("insertAccount");
            assertNull(insertAccount.getMaxBench());
            assertNull(insertAccount.getMaxDeadlift());
            assertNull(insertAccount.getMaxSquat());
            assertNull(insertAccount.getName());
            assertNull(insertAccount.getWorkouts());
        }

        @Test
        public void shouldQueryRepositoryForAllRecords() throws Exception {
            ctrl.home(map);

            verify(repository, only()).findAll();
        }

        @Test
        public void shouldAddRecordsFromRepositoryToModelMap() throws Exception {
            when(repository.findAll()).thenReturn(Arrays.asList(new Account(), new Account(), new Account()));

            ctrl.home(map);

            assertThat(map, hasKey("accounts"));
            assertTrue(map.get("accounts") instanceof List);

            List<Account> accounts = getRecords();
            assertThat(accounts, hasSize(3));
        }

        @SuppressWarnings("unchecked")
        private List<Account> getRecords() {
            return (List<Account>) map.get("records");
        }
    }

    public class InsertData {

        private MapBindingResult bindingResult;

        @Before
        public void setUp() throws Exception {
            bindingResult = new MapBindingResult(new HashMap<>(), "insertRecord");
        }

        @Test
        public void shouldSaveRecordWhenThereAreNoErrors() throws Exception {
            Account account = new Account();
            insertData(account);

            verify(repository, times(1)).save(account);
        }

        @Test
        public void shouldNotSaveRecordWhenThereAreErrors() throws Exception {
            bindingResult.addError(new ObjectError("", ""));

            insertData(new Account());

            verify(repository, never()).save(any(Account.class));
        }

        @Test
        public void shouldAddNewInsertRecordToModelMap() throws Exception {
            Account account = new Account();
            insertData(account);

            assertThat(map, hasKey("insertRecord"));
            assertThat(map.get("insertRecord"), is(not(account)));
        }

        @Test
        public void shouldAddRecordsToModelMap() throws Exception {
            insertData(new Account());

            assertThat(map, hasKey("records"));
        }

        private void insertData(Account account) {
            ctrl.insertData(map, account, bindingResult);
        }
    }
}
